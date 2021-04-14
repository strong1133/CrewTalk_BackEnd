
import React, {useState, useEffect} from 'react';
import axios from "axios";

function Hello(){
    const [users, setUsers] = useState([]);

    const fetchUsers = async () => {

        const response = await axios.get(
            'http://localhost:8080/api/hello'
        );
        setUsers(response.data);
        console.log(response)
    };

    useEffect(() => {
        fetchUsers();
    }, []);

    return (

        <div>
            {users}
        </div>

    );
}

export default Hello;