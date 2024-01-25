---
---
---

insert into yb_yftt_demo (id, message, updated_date)
select uuid('5de2fd48-51bf-41cd-a7a1-' || lpad(seq::text, 12, '0')),
       'MESSAGE-' || seq,
       clock_timestamp()
from generate_series(0, 1000) as seq;