# Seeding from file to a mysql container.
cat site/sql/easysqlseed.sql | docker exec -i easydb /usr/bin/mysql -u root --password=I+m8*yu^yLUHiZ*i id20315379_easydb