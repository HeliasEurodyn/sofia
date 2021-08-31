FROM nginx:1.17.1-alpine

ARG BACKEND_URL

COPY nginx.conf /etc/nginx/nginx.conf
COPY /dist /usr/share/nginx/html

RUN touch /usr/share/nginx/html/assets/config.js
RUN echo "window['backend_url'] = '"${BACKEND_URL}"';" >> /usr/share/nginx/html/assets/config.js
