# K3s metrics
We performed three experiments using the mapper script:
+ One device sending MQTT updates
+ Ten devices sending MQTT updates concurrently
+ Fifty devices sending MQTT updates concurrently

The results were obtained with Prometheus and Grafana.

### Master node CPU and RAM usage
| devices | CPU_mean | CPU_max | CPU_min | RAM_mean (GB) | RAM_max (GB) | RAM_min (GB) | 
|---------|----------|---------|---------|---------------|--------------|--------------| 
| idle    | 0.271    | 0.28    | 0.259   | 1.087         | 1.095        | 1.081        | 
| 1       | 0.292    | 0.297   | 0.286   | 1.15          | 1.15         | 1.15         | 
| 10      | 0.279    | 0.289   | 0.27    | 1.149         | 1.149        | 1.149        | 
| 50      | 0.264    | 0.313   | 0.226   | 1.158         | 1.167        | 1.156        | 

### Worker node CPU and RAM usage
| devices | CPU_mean | CPU_max | CPU_min | RAM_mean (GB) | RAM_max (GB) | RAM_min (GB) | 
|---------|----------|---------|---------|---------------|--------------|--------------| 
| idle    | 0.235    | 0.246   | 0.229   | 1.647         | 1.652        | 1.644        | 
| 1       | 0.287    | 0.321   | 0.253   | 2.246         | 2.249        | 2.244        | 
| 10      | 0.646    | 0.774   | 0.517   | 2.222         | 2.225        | 2.219        | 
| 50      | 0.855    | 1.046   | 0.279   | 2.309         | 2.344        | 2.291        | 

### Pod CPU and RAM usage
| devices | CPU_mean | CPU_max | CPU_min | RAM_mean (MB) | RAM_max (MB) | RAM_min (MB) | 
|---------|----------|---------|---------|---------------|--------------|--------------| 
| idle    | 0.016    | 0.017   | 0.012   | 238.075       | 239.051      | 237.019      | 
| 1       | 0.046    | 0.085   | 0.018   | 400.307       | 400.38       | 400.183      | 
| 10      | 0.272    | 0.553   | 0.019   | 396.046       | 400.347      | 393.966      | 
| 50      | 0.624    | 0.804   | 0.021   | 407.597       | 411.382      | 400.65       | 
