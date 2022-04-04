import BasicButton from "../UI/reusable/BasicButton";
import { Container } from "../styles/layout/Container";
import ToggleButton from "../UI/reusable/ToggleButton";

function Main() {
    return (
      <div>
          <BasicButton content='햄버거'></BasicButton>
          <ToggleButton content='포함' toggledContent='미포함'></ToggleButton>
      </div>
    );
  }
  
  export default Main;
  