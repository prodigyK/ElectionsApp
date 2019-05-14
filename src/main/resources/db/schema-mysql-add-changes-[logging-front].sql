
# Add new column to bf_subscriber table to store time of changes
ALTER TABLE bf_subscriber ADD COLUMN changed DATETIME AFTER email;

# Add new colunm to bf_log_main table to track if it is a new person or existing
ALTER TABLE bf_log_main ADD COLUMN is_new BOOLEAN AFTER changed;

# Module
INSERT INTO bf_module(name, description, reference, enabled, visible, permission)
  VALUES ('История изменений', 'История изменений', 'modules/module-logging', TRUE, TRUE, 'MODULE_LOGGING');
