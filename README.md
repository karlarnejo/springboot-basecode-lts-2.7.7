# Spring boot basecode using 3.0.2

# Ereate rsa key pair
openssl genrsa -out keypair.pem 2048

# Extract public key
openssl rsa -in keypair.pem -pubout -out public.pem

# Create private key in PKCS#8 format
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem

Delete **keypair.pem** after everything is done.

# RSA location
Store the **private.pem** and **public.pem** under *src/main/resources/certs*