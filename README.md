# Spring Boot and HikariCP on YugabyteDB

## Overview

This demo project is the source for examples for the 1/26/24 [YugabyteDB Friday
Tech Talk](https://www.yugabyte.com/yftt/).

This project demonstrates the complete [HikariCP](https://github.com/brettwooldridge/HikariCP)
and YugabyteDB [JDBC Smart Driver](https://docs.yugabyte.com/preview/reference/drivers/java/yugabyte-jdbc-reference/)
configuration using Spring Boot.

## Initial Setup

To set up and run YugabyteDB, you can either run a single node
via [docker](https://docs.yugabyte.com/preview/quick-start/docker/)
or use [yugabyted](https://docs.yugabyte.com/preview/reference/configuration/yugabyted/).

This demo uses `yugabyted` to simulate a multi-region deployment.

## Launch the Database

Use `yugabyted` to set up a 3 node cluster simulating a 3 region RF3 cluster. Note this setup runs cleanly in
Linux but may not on MacOS (due to networking differences and resource limitations). That said, the application assumes
this configuration with it's `topology-keys` limiting connections to `gcp.us-east1.*`. However, it should fall back to
vanilla load balanced if connection to a single node docker instance.

### Start the "east" node:

```shell
yugabyted start --advertise_address=127.0.0.1 --base_dir=/tmp/ybd-gcp1 --cloud_location=gcp.us-east1.us-east1-c
```

### Start the "central" node:

```shell
yugabyted start --advertise_address=127.0.0.2 --base_dir=/tmp/ybd-gcp2 --cloud_location=gcp.us-central1.us-central1-b --join=127.0.0.1
```

### Start the "west" node:

```shell
yugabyted start --advertise_address=127.0.0.3 --base_dir=/tmp/ybd-gcp3 --cloud_location=gcp.us-west1.us-west1-a --join=127.0.0.1
```

Finally, confirm the cluster status:

```shell
yugabyted status --base_dir=/tmp/ybd-gcp1
```

## Launch the Application

```shell
./gradlew bootRun
```

(or just run in your favorite IDE)

## Metrics

### Run Prometheus Container

```shell
docker run -d --name=prometheus -p 9090:9090 -v ./infra/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml
```

Launch http://localhost:9090

PromQL:

```
100 * (hikaricp_connections_active / hikaricp_connections_max)
```

Evaluates the percentage of connections used versus the max. As this approaches 100%,
there will be more delay waiting for the next available connect (increased latency).

Consider using a metric like this to trigger scale-out events at the application layer.

### Use Apache Bench to Test Connection Usage

Now launch an Apache Bench on a specific URL to test out connection usage (this isn't a really
great test, but it does tie up connections with the database):

```shell
ab -n 1000000 -c 15 http://localhost:8080/api/demo/5de2fd48-51bf-41cd-a7a1-000000000005
```
