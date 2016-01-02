#!/system/bin/sh

mount -o remount,rw -t ext4 /dev/block/mmcblk0p9 /system

cp /data/data/com.wubydax.romcontrol/files/scripts/update.zip /cache/update.zip 
cp /data/data/com.wubydax.romcontrol/files/scripts/command.bin /cache/recovery/command
chmod 0644 /cache/update.zip
chmod 0755 /cache/recovery/command
reboot recovery