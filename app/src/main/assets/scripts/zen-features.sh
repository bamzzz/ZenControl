#!/system/bin/sh

mount -o remount,rw -t ext4 /dev/block/mmcblk0p9 /system

cp /data/data/com.wubydax.romcontrol/files/scripts/asus.hardware.glove.xml /system/etc/permissions/asus.hardware.glove.xml
cp /data/data/com.wubydax.romcontrol/files/scripts/asus.hardware.touchgesture.double_tap.xml /system/etc/permissions/asus.hardware.touchgesture.double_tap.xml
cp /data/data/com.wubydax.romcontrol/files/scripts/asus.software.whole_system_onehand.xml /system/etc/permissions/asus.software.whole_system_onehand.xml
chmod 0644 /system/etc/permissions/asus.hardware.glove.xml
chmod 0644 /system/etc/permissions/asus.hardware.touchgesture.double_tap.xml
chmod 0644 /system/etc/permissions/asus.software.whole_system_onehand.xml
