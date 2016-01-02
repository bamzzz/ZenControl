#!/system/bin/sh

mount -o remount,rw -t ext4 /dev/block/mmcblk0p9 /system

cp /data/data/com.wubydax.romcontrol/files/scripts/settings.db /data/data/com.android.providers.settings/databases/settings.db
cp /data/data/com.wubydax.romcontrol/files/scripts/settings.db-journal /data/data/com.android.providers.settings/databases/settings.db-journal
chmod 0660 /data/data/com.android.providers.settings/databases/settings.db
chmod 0666 /data/data/com.android.providers.settings/databases/settings.db-journal
reboot
