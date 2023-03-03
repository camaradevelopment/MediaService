-- To create a procedure you will replace its PROCEDURE_NAME with the descriptive name of the procedure
-- We declare our paramitors with IN followed by a name and value. Needed for every param given to procedure
-- We declare return values with OUT followed by a name and value. Needed for every Selected Value. Name will be returned Collom value
CREATE PROCEDURE PROCEDURE_NAME(IN PARAMITOR VARCHAR(50), OUT RETURN_VALUE PROCEDURE_NAMEPROCEDURE_NAME INT)
BEGIN
SELECT COUNT(*) into count_out from car WHERE coloum = PARAMITOR;
END