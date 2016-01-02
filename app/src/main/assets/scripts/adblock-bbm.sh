#!/system/bin/sh

mount -o remount,rw -t ext4 /dev/block/mmcblk0p9 /system

pkill com.bbm
rm -rf /data/data/com.bbm/files/bbmcore/adsImages
mkdir /data/data/com.bbm/files/bbmcore/adsImages
chmod 0000 /data/data/com.bbm/files/bbmcore/adsImages
chmod 0000 /data/data/com.bbm/files/bbmcore/ads.db
chmod 0000 /data/data/com.bbm/files/bbmcore/ads.db-journal
chmod 0000 /data/data/com.bbm/files/bbmcore/bbmads.cfg
pkill zygote