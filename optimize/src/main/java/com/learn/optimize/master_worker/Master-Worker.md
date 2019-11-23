## Master-Worker模式
### 核心思想
   * 系统由两类进程协作工作：Master进程和Worker进程。Master进程负责接收和分配任务，Worker进程负责处理子任务。当各个Worker进程将子任务处理完成后，将结果返回给Master进程，由Master进程做归纳和汇总，从而得到系统的最终结果。