# Docker Setup for Choosenfly Hotel Booking System

This document provides instructions for running the Choosenfly Hotel Booking System using Docker and Docker Compose.

## Prerequisites

- Docker Desktop installed and running
- Docker Compose (included with Docker Desktop)
- Your existing PostgreSQL database dump file

## Project Structure

```
├── docker-compose.yml              # Docker Compose configuration
├── Dockerfile                      # Spring Boot application container
├── .env.example                    # Environment variables template
├── docker/
│   └── postgres-init/
│       ├── init-db.sh             # Database initialization script
│       ├── backup.dump            # Your PostgreSQL dump file (you need to add this)
│       └── README.md              # Database initialization instructions
└── src/main/resources/
    └── application-docker.properties  # Docker-specific application configuration
```

## Quick Start

### 1. Prepare Your Database Dump

Place your PostgreSQL database dump file in the `docker/postgres-init/` directory and name it `backup.dump`:

```bash
# Create a dump from your existing database
pg_dump -h localhost -p 5432 -U postgres -d hotelbookingsystem -Fc -f docker/postgres-init/backup.dump
```

### 2. Build Your Application

Build the Spring Boot JAR file:

```bash
# Using Maven
./mvnw clean package -DskipTests

# Or using Maven Wrapper on Windows
mvnw.cmd clean package -DskipTests
```

### 3. Start the Services

```bash
# Start all services in detached mode
docker-compose up -d

# Or start with logs visible
docker-compose up
```

### 4. Verify the Setup

- **Application**: http://localhost:8080
- **Health Check**: http://localhost:8080/actuator/health
- **Database**: localhost:5432 (accessible from host)

## Services

### Backend Service
- **Container Name**: `choosenfly-backend`
- **Port**: 8080
- **Image**: Built from Dockerfile
- **Profile**: `docker`
- **Health Check**: Actuator health endpoint

### Database Service
- **Container Name**: `choosenfly-postgres-db`
- **Image**: postgres:17-alpine
- **Port**: 5432
- **Database**: hotelbookingsystem
- **User**: postgres
- **Password**: root
- **Health Check**: pg_isready

## Configuration

### Environment Variables

The application uses the following environment variables (defined in docker-compose.yml):

- `SPRING_PROFILES_ACTIVE=docker`
- `POSTGRES_DB=hotelbookingsystem`
- `POSTGRES_USER=postgres`
- `POSTGRES_PASSWORD=root`

### Application Properties

The `application-docker.properties` file contains Docker-specific configurations:

- Database URL: `jdbc:postgresql://db:5432/hotelbookingsystem`
- Hibernate DDL: `update`
- Logging configuration for containerized environment

## Data Persistence

- **PostgreSQL Data**: Stored in named volume `postgres_data`
- **Application Logs**: Stored in named volume `app_logs`
- **File Uploads**: Mounted to `./uploads` directory

## Common Commands

### Start Services
```bash
docker-compose up -d
```

### Stop Services
```bash
docker-compose down
```

### View Logs
```bash
# All services
docker-compose logs

# Specific service
docker-compose logs backend
docker-compose logs db
```

### Rebuild Application
```bash
# Rebuild and restart backend service
docker-compose up -d --build backend
```

### Database Operations
```bash
# Connect to PostgreSQL
docker exec -it choosenfly-postgres-db psql -U postgres -d hotelbookingsystem

# Backup database
docker exec choosenfly-postgres-db pg_dump -U postgres hotelbookingsystem > backup.sql

# Restore database (if needed)
docker exec -i choosenfly-postgres-db psql -U postgres -d hotelbookingsystem < backup.sql
```

### Clean Up
```bash
# Stop and remove containers, networks
docker-compose down

# Also remove volumes (WARNING: This deletes all data)
docker-compose down -v

# Remove unused Docker resources
docker system prune
```

## Troubleshooting

### Application Won't Start
1. Check if the JAR file exists in `target/` directory
2. Verify database is healthy: `docker-compose logs db`
3. Check application logs: `docker-compose logs backend`

### Database Connection Issues
1. Ensure database container is healthy: `docker-compose ps`
2. Check database logs: `docker-compose logs db`
3. Verify database credentials in `application-docker.properties`

### Database Initialization Issues
1. Check if `backup.dump` file exists in `docker/postgres-init/`
2. Verify init script permissions: `chmod +x docker/postgres-init/init-db.sh`
3. Check initialization logs: `docker-compose logs db`

### Port Conflicts
If ports 8080 or 5432 are already in use, modify the port mappings in `docker-compose.yml`:

```yaml
services:
  backend:
    ports:
      - "8081:8080"  # Change host port to 8081
  
  db:
    ports:
      - "5433:5432"  # Change host port to 5433
```

## Development Workflow

### Making Code Changes
1. Make your code changes
2. Rebuild the JAR: `./mvnw clean package -DskipTests`
3. Rebuild and restart: `docker-compose up -d --build backend`

### Database Schema Changes
- The application uses `hibernate.ddl-auto=update` in Docker mode
- Schema changes will be applied automatically when the application starts
- For major changes, consider creating a new database dump

## Production Considerations

For production deployment, consider:

1. **Security**: Change default passwords and use environment variables
2. **SSL/TLS**: Configure HTTPS and database SSL connections
3. **Resource Limits**: Add memory and CPU limits to services
4. **Monitoring**: Add monitoring and logging solutions
5. **Backup Strategy**: Implement automated database backups
6. **Secrets Management**: Use Docker secrets or external secret management

## Support

If you encounter issues:

1. Check the logs: `docker-compose logs`
2. Verify all files are in place according to the project structure
3. Ensure your database dump is compatible with PostgreSQL 17
4. Check Docker and Docker Compose versions
