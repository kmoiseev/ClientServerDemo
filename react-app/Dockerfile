FROM node:13.7.0 as build

WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . /app

RUN npm run build

FROM nginx:1.17.8

EXPOSE 80
COPY --from=build /app/build /usr/share/nginx/html

ENTRYPOINT ["nginx","-g","daemon off;"]