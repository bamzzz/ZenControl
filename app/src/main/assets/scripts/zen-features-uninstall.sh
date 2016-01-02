#!/system/bin/sh

mount -o remount,rw -t ext4 /dev/block/mmcblk0p9 /system

rm /system/etc/permissions/asus.hardware.glove.xml
rm /system/etc/permissions/asus.hardware.touchgesture.double_tap.xml
rm /system/etc/permissions/asus.software.whole_system_onehand.xml
