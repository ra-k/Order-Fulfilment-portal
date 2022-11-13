import { NavLink as ReactLink } from 'react-router-dom';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';
import { isLoggedIn } from '../auth';
import { doLogout } from '../auth';
import { getCurrentUserDetail } from '../auth';

import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
 
} from 'reactstrap';


const CustomNavbar =()=> {

  let navigate = useNavigate()
  const [isOpen, setIsOpen] = useState(false);
  
  const [login, setLogin] = useState(false)
 const [ employe,setEmploye] = useState(undefined)

  useEffect(() => {

      setLogin(isLoggedIn())
      setEmploye(getCurrentUserDetail())

  }, [login])


  const logout = () => {
      doLogout(() => {
          //logged out
          setLogin(false)
          

          navigate("/")
      })
  }

  

  return (
    <div>
      <Navbar
      color="dark"
      dark
      expand="md"
      fixed=""
      className="px-5"
      >
       
        <NavbarBrand tag={ReactLink} to="/">
          At-Home
          </NavbarBrand>
        <NavbarToggler onClick={()=> setIsOpen(!isOpen)} />
        <Collapse isOpen={isOpen} navbar>
          <Nav className="me-auto" navbar>
            <NavItem>
              <NavLink tag={ReactLink} to="/about">About</NavLink>
            </NavItem>

            <NavItem>
              <NavLink tag={ReactLink} to="/user/table">Table</NavLink>
            </NavItem>
            
          </Nav >
          
          <Nav navbar>
            {
              login &&(
                <>


                                 <NavItem>
                                    <NavLink tag={ReactLink} to="/user/upload" >
                                        {employe.email}
                                    </NavLink>
                                </NavItem> 


                                    <NavItem>
                                        <NavLink onClick={logout} >
                                            Logout
                                        </NavLink>
                                    </NavItem>
                                </>


                 
                                  


                 
                                       														


                    
                                  	
                


              


               
              )

            }
            {
              !login && (
              
              <>


                                   <NavItem>
                                        <NavLink tag={ReactLink} to="/login" >
                                            Login
                                        </NavLink>
                                    </NavItem>
                                    <NavItem>
                                        <NavLink tag={ReactLink} to="/signup" >
                                            Signup
                                        </NavLink>
                                    </NavItem>
             
              </>


              )
            }

          

          </Nav>
          
        </Collapse>
      </Navbar>
    </div>
  )
}

export default CustomNavbar;