```
{
getHello(name: "test") {
  greeting
  }
}
```

Query service
```
curl \
-X POST \
-H "Content-Type: application/json" \
--data '{ "query": "{ getHello(name: \"test\") { greeting } } " }' \
http://localhost:9000/api/graphql/ 
```

Query schema:
```
curl -i -X POST http://localhost:9000/api/graphql -H "Content-Type: application/json" -d @introspection_query.json
```

You can use `jq` to pretty format.
