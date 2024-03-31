--liquibase formatted sql

--changeset f1-add-admin_user
ALTER TABLE employee ADD COLUMN is_admin BOOLEAN DEFAULT false;
UPDATE employee SET is_admin = false WHERE employee.is_admin IS NULL;
