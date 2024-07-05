package com.example.application_flamingo

data class GetDataStore(val location_store: String? = null,
                        val name_store: String? = null,
                        val rental_store: String? = null,
                        val imageStore: String? = null,
                        val description: String? = null,
                        val ocenka: String? = null,
                        val ocenka_user: String? = null,
                        val numberStore: String? = null)