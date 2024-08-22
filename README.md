# Spring Data Reactive Demo
Built as a technology demo by Krys Petrie for a tech Spike presentation given on August 15th, 2024.
### Project Overview
This application is a basic Spring Reactive web-app that uses a Postgres database, Flyway, and Spring Data R2DBC. This barebones application was built as an alternative implementation to the [Spring Data REST Demo](https://github.com/kryspetrie/Spring-Data-REST-Demo) application, without use of Spring Data REST. Similarly, it models a simple animal inventory for zoos, where you have a zoo, an animal type, and a linkage between the two, which includes counts. However, this implementation exposes far fewer endpoints, as it is intended as purely an example application. Visit the [Spring Data REST Demo](https://github.com/kryspetrie/Spring-Data-REST-Demo) readme for general takeaways.

Note: Before starting the application, you must <code>docker compose up -d</code> to start the postgres database.

### Example API Endpoints
This application uses JSON and only has a few exposed endpoints. Application root: [http://localhost:8080](http://localhost:8080)

<table>
<thead>
  <th>Description</th>
  <th>Path</th>
  <th>Headers</th>
</thead>
<tbody>
<tr>
<td>Get animals, paged</td>
<td><code>GET http://localhost:8080/animal/paged?page=0&size=1</code></td>
<td><code>Accept=application/json</code></td>
</tr>
<tr>
<td>Get animal stream, paged</td>
<td><code>GET http://localhost:8080/animal</code></td>
<td><code>Accept=application/x-ndjson</code></td>
</tr>
<tr>
</tbody>
</table>