
# Marvel Character List 

Marvel application that show character list with details 


## Appendix

In this small project I'm using MVVM with Clean Architecture, the project also have UnitTest 


## Libraries used

- Retrofit
- Gson
- glide
- Mockito
- Junit
- Dagger Hilt
- Lottie
- Okhttp


## API Reference

#### Get all Characters

```http
  GET GET /v1/public/characters
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get character by Id

```http
  GET  /v1/public/characters/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |




## Libraries Used

  retrofit_version = "2.9.0"
  
  


## Authors

- [@CristhianNY](https://www.github.com/CristhianNY)


# Architecture 


![Clean Architecture](https://devexperto.com/wp-content/uploads/2018/10/clean-architecture-interaction.png)
