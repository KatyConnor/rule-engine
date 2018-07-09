namespace hx.com.example.rule.dao.TestObjectMapperDAO;
import hx.com.example.rule.dto.TestObjectDTO testObjectDTO;

column baseColumn (id,name,version,create_time,update_time)
column baseSelectColumn (id,name,version,create_time,update_time)

method queryList:
SELECT column(testObjectDTO) FROM table(testObjectDTO) where param;

## 表自己设置了别名需要自己注明别名
method queryAll:
SELECT column(obj.testObjectDTO),column(dto.testObjectDTO) FROM test_object AS obj
left table(testObjectDTO) AS dto oin on param.id = param1.id where param AND param.name = value(param1.name);

method insertObject:
INSERT INTO table(testObjectDTO) (column(testObjectDTO)) VALUES foreach(param -> {
  SELECT id FROM table WHERE id param.id
})

method insertList:
 foreach(param -> {
    INSERT INTO table (column(testObjectDTO)) VALUES (testObjectDTO);
})

UPDATE tablename SET name = '' WHERE id IN (
    foreach(id -> {
      #{id},
  },trim(,))
)

 INSERT INTO table (column(testObjectDTO)) VALUES
  param1.foreach(dto -> {
      (dto),
  },trim(,))

 INSERT INTO table (column(testObjectDTO)) VALUES
 ( param1.foreach( (k,v) -> {
      v,#{k}
  },trim(,)) )


SELECT column(obj.testObjectDTO),column(dto.testObjectDTO) FROM test_object AS obj,B AS b
WHERE obj.id = b.id AND b.id IN (SELECT column(o.testObjectDTO) FROM test_object AS o WHERE o.name = '');


left table(testObjectDTO) AS dto oin on param.id = param1.id where param AND param.name = value(param1.name);