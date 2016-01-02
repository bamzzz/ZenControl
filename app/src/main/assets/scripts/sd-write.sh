#!/system/bin/sh

mount -o remount,rw -t ext4 /dev/block/mmcblk0p9 /system

cp /data/data/com.wubydax.romcontrol/files/scripts/platform.xml /system/etc/permissions/platform.xml
chmod 0644 /system/etc/permissions/platform.xml
