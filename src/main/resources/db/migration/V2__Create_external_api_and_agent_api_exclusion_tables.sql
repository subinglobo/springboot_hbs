-- Create external_api table
CREATE TABLE external_api (
    id BIGSERIAL PRIMARY KEY,
    api_code VARCHAR(50) NOT NULL UNIQUE,
    api_name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    is_external BOOLEAN NOT NULL DEFAULT TRUE,
    created_by VARCHAR(100),
    created_date TIMESTAMP,
    updated_by VARCHAR(100),
    updated_date TIMESTAMP,
    
    CONSTRAINT uk_external_api_code UNIQUE (api_code)
);

-- Create agent_api_exclusion table
CREATE TABLE agent_api_exclusion (
    id BIGSERIAL PRIMARY KEY,
    agent_id BIGINT NOT NULL,
    external_api_id BIGINT NOT NULL,
    is_excluded BOOLEAN NOT NULL DEFAULT TRUE,
    exclusion_reason VARCHAR(500),
    created_by VARCHAR(100),
    created_date TIMESTAMP,
    updated_by VARCHAR(100),
    updated_date TIMESTAMP,
    
    CONSTRAINT fk_agent_api_exclusion_agent_id 
        FOREIGN KEY (agent_id) REFERENCES agent(id) 
        ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT fk_agent_api_exclusion_external_api_id 
        FOREIGN KEY (external_api_id) REFERENCES external_api(id) 
        ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT uk_agent_api_exclusion_agent_api 
        UNIQUE (agent_id, external_api_id)
);

-- Create indexes for better query performance
CREATE INDEX idx_external_api_code ON external_api(api_code);
CREATE INDEX idx_external_api_active ON external_api(is_active);
CREATE INDEX idx_external_api_external ON external_api(is_external);

CREATE INDEX idx_agent_api_exclusion_agent_id ON agent_api_exclusion(agent_id);
CREATE INDEX idx_agent_api_exclusion_external_api_id ON agent_api_exclusion(external_api_id);
CREATE INDEX idx_agent_api_exclusion_excluded ON agent_api_exclusion(is_excluded);

-- Note: Default APIs will be registered through the admin interface
-- This allows for dynamic API registration without requiring database migrations

-- Add comments for documentation (PostgreSQL syntax)
COMMENT ON TABLE external_api IS 'Registry of external APIs available in the system';
COMMENT ON COLUMN external_api.api_code IS 'Unique identifier for the API';
COMMENT ON COLUMN external_api.api_name IS 'Display name of the API';
COMMENT ON COLUMN external_api.is_active IS 'Whether the API is currently active';
COMMENT ON COLUMN external_api.is_external IS 'Whether the API is external (can be excluded)';

COMMENT ON TABLE agent_api_exclusion IS 'Tracks which external APIs are excluded for specific agents';
COMMENT ON COLUMN agent_api_exclusion.agent_id IS 'Foreign key reference to agent table';
COMMENT ON COLUMN agent_api_exclusion.external_api_id IS 'Foreign key reference to external_api table';
COMMENT ON COLUMN agent_api_exclusion.is_excluded IS 'Whether the API is excluded for this agent';
COMMENT ON COLUMN agent_api_exclusion.exclusion_reason IS 'Reason for excluding the API';
