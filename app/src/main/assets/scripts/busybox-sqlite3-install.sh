#!/system/bin/sh

mount -o remount,rw -t ext4 /dev/block/mmcblk0p9 /system
mount -o rw,remount /system

cp /data/data/com.wubydax.romcontrol/files/scripts/sqlite3.bin /system/xbin/sqlite3
cp /data/data/com.wubydax.romcontrol/files/scripts/busybox-x86.png /system/xbin/busybox
chmod 0755 /system/xbin/sqlite3
chmod 0755 /system/xbin/busybox
/system/xbin/busybox --install -s /system/xbin
ln -s /system/xbin/busybox /system/bin/busybox