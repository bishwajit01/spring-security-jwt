||=======================================||
||    HOW TO USE JWT as AUTHORIZATION    || 
||=======================================||

*********
* Steps *
*********
1. Open POSTMAN, select POST
2. Enter the URL, localhost:9091/authenticate
3. In Body, select raw and text type as JSON. ENter the below json in Body.
{
	"username":"user",
	"password":"pass2"
}
5. In Header, key = "Content-Type", Value = "application/json"
6. After this hit SEND button.
7. You will get the jwt response.
{
	"jwt":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTg3OTMxNDczLCJpYXQiOjE1ODc4OTU0NzN9.WmU7Ukm1x185M6mI53a3f6YLK3N2D18neVEuINzqHVA"
}
8. Then change the httpRequest to GET.
9. Enter the URL, localhost:9091/home
10. In Header fill the below Value,
		key = "Authorization", value = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTg3OTMxNDczLCJpYXQiOjE1ODc4OTU0NzN9.WmU7Ukm1x185M6mI53a3f6YLK3N2D18neVEuINzqHVA"
		key = "Content-Type", value = "application/json"
11. Click on send button, you receive the response.