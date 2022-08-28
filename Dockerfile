FROM alpine:latest

LABEL org.opencontainers.image.source="https://github.com/MeProjectStudio/pteroctl"
LABEL org.opencontainers.image.licenses="GPL-3.0"

WORKDIR /app
ENV PATH /app:$PATH

COPY --chmod=755 pteroctl* /app

CMD ["pteroctl"]