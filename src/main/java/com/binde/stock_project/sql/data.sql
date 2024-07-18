
SELECT T1.id, T1.name, T1.age
FROM T1
         LEFT JOIN T2 ON T1.id = T2.id
WHERE T2.id IS NULL
ORDER BY T1.id;

#Alternatively
SELECT T1.*
FROM T1
         LEFT JOIN T2 ON T1.id = T2.id
WHERE T2.id IS NULL
ORDER BY T1.id;

#Explanation:
#SELECT T1.*: This selects all columns from the T1 table.
#LEFT JOIN T2 ON T1.id = T2.id: This performs a left join between T1 and T2 on the id column. The left join ensures that all records from T1 are included, and matching records from T2 are also included if they exist.
#WHERE T2.id IS NULL: This condition filters out the rows where there is a match in T2. Essentially, it keeps only the rows from T1 that do not have a corresponding match in T2.
#ORDER BY T1.id: This orders the result set based on the id column of T1.