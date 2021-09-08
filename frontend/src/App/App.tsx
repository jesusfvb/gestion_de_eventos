import axios from "axios";
import jwtService from "jsonwebtoken";
import { useState } from "react";
import MyRoutes from "./components/MyRoutes";
import Login from "./pages/Login";

axios.defaults.baseURL = "http://localhost:8080";

const App = () => {
  const [jwt, setJwt] = useState<string>();

  function login(username: string, password: string) {
    let data = new FormData();
    data.append("username", username);
    data.append("password", password);
    axios
      .post("/user/login", data)
      .then((response) => setJwt(response.data))
      .catch((error) => console.error(error));
  }

  function logout() {
    sessionStorage.clear();
    setJwt(undefined);
  }

  if (jwtService.decode(jwt + "") === null) {
    if (jwtService.decode(sessionStorage.getItem("jwt") + "") == null) {
      axios.defaults.headers.common = {
        Accept: "application/json, text/plain, */*",
      };
      return <Login login={login} />;
    }
    setJwt(sessionStorage.getItem("jwt") + "");
    return null;
  }
  axios.defaults.headers.common = {
    Accept: "application/json, text/plain, */*",
    Authorization: jwt,
  };
  sessionStorage.setItem("jwt", jwt + "");
  return <MyRoutes logout={logout} />;
};

export default App;
