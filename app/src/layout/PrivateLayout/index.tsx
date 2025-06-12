import Loading from "@/components/Loading";
import { Routes } from "@/constants/Routes";
import { useLogin } from "@/contexts/LoginContext/UseLogin";
import { Navigate, Outlet } from "react-router";

export default function PrivateLayout(){
  const { token, isLoading } = useLogin();

  if(isLoading) return <Loading />
  if(!token && !isLoading) return <Navigate to={Routes.LOGIN} replace/>

  return (
    <>
      <Outlet />
    </>
  )

}
