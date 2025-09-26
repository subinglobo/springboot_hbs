#!/bin/bash
set -e

# Database initialization script for PostgreSQL
# This script runs automatically when the PostgreSQL container starts for the first time

echo "Starting database initialization..."

# Wait for PostgreSQL to be ready
until pg_isready -h localhost -p 5432 -U postgres; do
  echo "Waiting for PostgreSQL to be ready..."
  sleep 2
done

echo "PostgreSQL is ready. Starting database restoration..."

# Check if backup.dump exists
if [ -f "/docker-entrypoint-initdb.d/backup.dump" ]; then
    echo "Found backup.dump file. Restoring database..."
    
    # Restore the database from the dump file
    # Using pg_restore with verbose output and clean option
    pg_restore \
        --host=localhost \
        --port=5432 \
        --username=postgres \
        --dbname=hotelbookingsystem \
        --verbose \
        --clean \
        --if-exists \
        --no-owner \
        --no-privileges \
        /docker-entrypoint-initdb.d/backup.dump
    
    echo "Database restoration completed successfully!"
else
    echo "No backup.dump file found. Skipping database restoration."
    echo "Please place your PostgreSQL dump file at ./docker/postgres-init/backup.dump"
fi

echo "Database initialization script completed."
