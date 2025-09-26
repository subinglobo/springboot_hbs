# PostgreSQL Database Initialization

This directory contains files for initializing the PostgreSQL database when the Docker container starts for the first time.

## Files

### init-db.sh
- **Purpose**: Automatically restores your database from a dump file
- **Execution**: Runs automatically when PostgreSQL container starts for the first time
- **Requirements**: Requires `backup.dump` file to be present in this directory

### backup.dump (You need to add this)
- **Purpose**: Your PostgreSQL database dump file
- **Format**: PostgreSQL custom format dump file
- **Location**: Place your dump file here as `backup.dump`

## How to Create a Database Dump

To create a dump of your existing PostgreSQL database, use the following command:

```bash
# Create a custom format dump (recommended)
pg_dump -h localhost -p 5432 -U postgres -d hotelbookingsystem -Fc -f backup.dump

# Or create a plain SQL dump
pg_dump -h localhost -p 5432 -U postgres -d hotelbookingsystem -f backup.sql
```

## Usage Instructions

1. **Place your database dump**: Copy your PostgreSQL dump file to this directory and name it `backup.dump`

2. **Start Docker containers**: Run `docker-compose up -d`

3. **First-time initialization**: The init script will automatically:
   - Wait for PostgreSQL to be ready
   - Restore your database from the dump file
   - Log the restoration process

4. **Subsequent starts**: The initialization only runs on the first container start. Data persists in the `postgres_data` volume.

## Troubleshooting

- **Check logs**: `docker-compose logs db` to see initialization logs
- **Manual restore**: If automatic restore fails, you can manually restore:
  ```bash
  docker exec -i choosenfly-postgres-db pg_restore -U postgres -d hotelbookingsystem < backup.dump
  ```
- **Reset database**: To re-run initialization, remove the volume:
  ```bash
  docker-compose down -v
  docker-compose up -d
  ```

## File Permissions

Make sure the init-db.sh script has execute permissions:
```bash
chmod +x docker/postgres-init/init-db.sh
```
