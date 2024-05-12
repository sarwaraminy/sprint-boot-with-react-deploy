import { Link } from 'react-router-dom'

const NavLink = () => {
    return(
        <nav>
         <ul>
            <li><Link to="/rooms/api">Room</Link></li>
         </ul>
         <hr />
        </nav>
    );
}

export default NavLink;