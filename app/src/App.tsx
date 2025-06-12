import { createBrowserRouter } from "react-router"
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import { RouterProvider } from "react-router";
import LoginContextProvider from "./contexts/LoginContext";
import RegisterContextProvider from "./contexts/RegisterContext";
import PrivateLayout from "./layout/PrivateLayout";

const router = createBrowserRouter([
  {
    path: "/",
    Component: PrivateLayout,
    children: [
      {
        index: true,
        Component: Home
      }
    ]
  },
  {
    path: "/login",
    Component: Login
  },
  {
    path: "/register",
    Component: Register
  },
]);

function App() {

  return (
    <>
      <RegisterContextProvider>
        <LoginContextProvider>
          <RouterProvider router={router} />
        </LoginContextProvider>
      </RegisterContextProvider>
    </>
  )
}

export default App
