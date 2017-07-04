marvel_hash_params <- function() {

  ts <- round(as.numeric(Sys.time())*1000) # can totally be just Sys.time(), too
  to_hash <- sprintf("%s%s%s",
                     ts,
                     Sys.getenv("MARVEL_API_PRIVATE_KEY"),
                     Sys.getenv("MARVEL_API_PUBLIC_KEY"))

  list(
    ts=ts,
    hash=digest::digest(to_hash, "md5", FALSE),
    apikey=Sys.getenv("MARVEL_API_PUBLIC_KEY")
  )

}
