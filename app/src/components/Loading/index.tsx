import Styles from "./styles.module.css";

export default function Loading(){
  return (
    <div className="h-screen w-full flex items-center justify-center">
      <div className={Styles.loader}>
      </div>
    </div>
  )
}
