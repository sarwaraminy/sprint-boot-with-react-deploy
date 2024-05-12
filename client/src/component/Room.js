
import { useState, useEffect } from 'react';

const RoomData = () => {
    const [roomList, setRoomList] = useState([]);
    useEffect(() => {
        fetch(
            'http://localhost:8080/rooms/api'
        ).then((response) => response.json())
        .then(data => setRoomList(data));
    }, [])
    return(
        <>
         <div class="container">
            <h1>Room data as restfull api, geted by ReactJS</h1>
            <div class="table-container">
               <table className='table'>
                  <thead class="sticky-header">
                      <th>Name</th><th>Room Number</th><th>Bed Info</th><th>Actions</th>
                  </thead>
                  <tbody>
                      {
                         roomList.map(rooms => (
                             <tr key={rooms.id}>
                                 <td>{rooms.name}</td><td>{rooms.roomNumber}</td> <td>{rooms.bedInfo}</td><td></td>
                             </tr>
                         ))
                      }
                  </tbody>
               </table>
            </div>
         </div>
        </>
    );
}

export default RoomData;