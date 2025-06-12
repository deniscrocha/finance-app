const API_URL = "http://localhost:8080/";

export enum ApiRoutes {
  LOGIN = API_URL+"auth/login",
  REGISTER = API_URL+"auth/register",
  VALIDATE_TOKEN = API_URL+"auth/validate",
}
