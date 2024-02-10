ALTER TABLE emsuser
ALTER COLUMN status TYPE boolean
USING status::boolean;
