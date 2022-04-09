import BasicButton from "../UI/reusable/BasicButton";
import { Container } from "../styles/layout/Container";
import ToggleButton from "../UI/reusable/ToggleButton";
import ConfirmButon from "../UI/reusable/ConfirmButton";
import Header from "../UI/reusable/Header";
import Intro from "../UI/main/Intro";

function Main() {
 return (
  <div>
   <Header />
   <Intro />
   <BasicButton content="햄버거"></BasicButton>
   <ToggleButton content="포함" toggledContent="미포함"></ToggleButton>
   <ConfirmButon content="찾아보기" />
  </div>
 );
}

export default Main;
