get_characters <- function(name) {

  params <- marvel_hash_params()
  params$name <- name

  res <- httr::GET("https://gateway.marvel.com:443/v1/public/characters",
                   query=params)

  httr::stop_for_status(res)

  httr::content(res, as="parsed")

}

get_characters("spider-man")
