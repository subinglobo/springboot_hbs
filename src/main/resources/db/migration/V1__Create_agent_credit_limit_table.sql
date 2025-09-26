-- Create agent_credit_limit table
CREATE TABLE agent_credit_limit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    agent_id BIGINT NOT NULL,
    total_credit_limit DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    available_credit_limit DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    created_by VARCHAR(100),
    created_date DATETIME,
    updated_by VARCHAR(100),
    updated_date DATETIME,
    
    CONSTRAINT fk_agent_credit_limit_agent_id 
        FOREIGN KEY (agent_id) REFERENCES agent(id) 
        ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT uk_agent_credit_limit_agent_id 
        UNIQUE (agent_id),
    
    CONSTRAINT chk_total_credit_limit_positive 
        CHECK (total_credit_limit >= 0),
    
    CONSTRAINT chk_available_credit_limit_positive 
        CHECK (available_credit_limit >= 0),
    
    CONSTRAINT chk_available_credit_limit_not_exceed_total 
        CHECK (available_credit_limit <= total_credit_limit)
);

-- Create index for better query performance
CREATE INDEX idx_agent_credit_limit_agent_id ON agent_credit_limit(agent_id);

-- Add comments for documentation
ALTER TABLE agent_credit_limit COMMENT = 'Stores credit limit information for agents';
ALTER TABLE agent_credit_limit MODIFY COLUMN agent_id BIGINT NOT NULL COMMENT 'Foreign key reference to agent table';
ALTER TABLE agent_credit_limit MODIFY COLUMN total_credit_limit DECIMAL(15,2) NOT NULL DEFAULT 0.00 COMMENT 'Total credit limit assigned to the agent';
ALTER TABLE agent_credit_limit MODIFY COLUMN available_credit_limit DECIMAL(15,2) NOT NULL DEFAULT 0.00 COMMENT 'Current available credit limit for the agent';
