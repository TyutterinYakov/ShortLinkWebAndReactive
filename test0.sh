#/bin/sh
clear

host=http://localhost:8080
read -p "Limit: " limit
echo ""

i=0
started=$(($(date +%s%N)/1000000))
total_threads=0

tput sc

while [ $i -lt $limit ];
do 
    json=$(curl -s "$host")
    threads=${json}
    total_threads=$((total_threads  + threads))
    i=$((i+1))

    ms=$(($(date +%s%N)/1000000))
    avg_duration=$((($ms-$started)/i))

    avg_threads=$(($total_threads/i))

    tput rc 
    tput ed
    echo "Request: ${i}/${limit}. Threads: ${threads}. Avg response time: ${avg_duration}ms. Avg threads: ${avg_threads} "
done

echo "Finished"






