FROM nginx:1.17.1-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY /dist /usr/share/nginx/html
RUN touch /usr/share/nginx/html/assets/config.js