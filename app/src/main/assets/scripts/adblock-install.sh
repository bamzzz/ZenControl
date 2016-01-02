#!/system/bin/sh

mount -o remount,rw -t ext4 /dev/block/mmcblk0p9 /system

cp /data/data/com.wubydax.romcontrol/files/scripts/hosts.me /system/etc/hosts
chmod 0644 /system/etc/hosts
