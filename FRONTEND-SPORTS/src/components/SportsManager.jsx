import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './style.css';

const SportsManager = () => {
  const [players, setPlayers] = useState([]);
  const [player, setPlayer] = useState({
    id: '',
    name: '',
    gender: '',
    sport: '',
    team: '',
    position: '',
    coach: '',
    level: '',
    email: '',
    contact: ''
  });
  const [idToFetch, setIdToFetch] = useState('');
  const [fetchedPlayer, setFetchedPlayer] = useState(null);
  const [message, setMessage] = useState('');
  const [editMode, setEditMode] = useState(false);

  const baseUrl = `${import.meta.env.VITE_API_URL}/sportsapi`;

  useEffect(() => {
    fetchAllPlayers();
  }, []);

  const fetchAllPlayers = async () => {
    try {
      const res = await axios.get(`${baseUrl}/all`);
      setPlayers(res.data);
    } catch (error) {
      setMessage('Failed to fetch players.');
    }
  };

  const handleChange = (e) => {
    setPlayer({ ...player, [e.target.name]: e.target.value });
  };

  const validateForm = () => {
    for (let key in player) {
      if (!player[key] || player[key].toString().trim() === '') {
        setMessage(`Please fill out the ${key} field.`);
        return false;
      }
    }
    return true;
  };

  const addPlayer = async () => {
    if (!validateForm()) return;
    try {
      await axios.post(`${baseUrl}/add`, player);
      setMessage('Player added successfully.');
      fetchAllPlayers();
      resetForm();
    } catch (error) {
      setMessage('Error adding player.');
    }
  };

  const updatePlayer = async () => {
    if (!validateForm()) return;
    try {
      await axios.put(`${baseUrl}/update`, player);
      setMessage('Player updated successfully.');
      fetchAllPlayers();
      resetForm();
    } catch (error) {
      setMessage('Error updating player.');
    }
  };

  const deletePlayer = async (id) => {
    try {
      const res = await axios.delete(`${baseUrl}/delete/${id}`);
      setMessage(res.data);
      fetchAllPlayers();
    } catch (error) {
      setMessage('Error deleting player.');
    }
  };

  const getPlayerById = async () => {
    try {
      const res = await axios.get(`${baseUrl}/get/${idToFetch}`);
      setFetchedPlayer(res.data);
      setMessage('');
    } catch (error) {
      setFetchedPlayer(null);
      setMessage('Player not found.');
    }
  };

  const handleEdit = (p) => {
    setPlayer(p);
    setEditMode(true);
    setMessage(`Editing player with ID ${p.id}`);
  };

  const resetForm = () => {
    setPlayer({
      id: '',
      name: '',
      gender: '',
      sport: '',
      team: '',
      position: '',
      coach: '',
      level: '',
      email: '',
      contact: ''
    });
    setEditMode(false);
  };

  return (
    <div className="sports-container">

      {message && (
        <div className={`message-banner ${message.toLowerCase().includes('error') ? 'error' : 'success'}`}>
          {message}
        </div>
      )}

      <h2>Sports Management</h2>

      <div>
        <h3>{editMode ? 'Edit Player' : 'Add Player'}</h3>
        <div className="form-grid">
          <input type="number" name="id" placeholder="ID" value={player.id} onChange={handleChange} />
          <input type="text" name="name" placeholder="Name" value={player.name} onChange={handleChange} />
          <select name="gender" value={player.gender} onChange={handleChange}>
            <option value="">Select Gender</option>
            <option value="MALE">MALE</option>
            <option value="FEMALE">FEMALE</option>
          </select>
          <select name="sport" value={player.sport} onChange={handleChange}>
            <option value="">Select Sport</option>
            <option value="Cricket">Cricket</option>
            <option value="Football">Football</option>
            <option value="Basketball">Basketball</option>
            <option value="Badminton">Badminton</option>
            <option value="Athletics">Athletics</option>
          </select>
          <input type="text" name="team" placeholder="Team" value={player.team} onChange={handleChange} />
          <input type="text" name="position" placeholder="Position (e.g. Goalkeeper, Batsman)" value={player.position} onChange={handleChange} />
          <input type="text" name="coach" placeholder="Coach Name" value={player.coach} onChange={handleChange} />
          <select name="level" value={player.level} onChange={handleChange}>
            <option value="">Select Level</option>
            <option value="College">College</option>
            <option value="District">District</option>
            <option value="State">State</option>
            <option value="National">National</option>
          </select>
          <input type="email" name="email" placeholder="Email" value={player.email} onChange={handleChange} />
          <input type="text" name="contact" placeholder="Contact" value={player.contact} onChange={handleChange} />
        </div>

        <div className="btn-group">
          {!editMode ? (
            <button className="btn-blue" onClick={addPlayer}>Add Player</button>
          ) : (
            <>
              <button className="btn-green" onClick={updatePlayer}>Update Player</button>
              <button className="btn-gray" onClick={resetForm}>Cancel</button>
            </>
          )}
        </div>
      </div>

      <div>
        <h3>Get Player By ID</h3>
        <input
          type="number"
          value={idToFetch}
          onChange={(e) => setIdToFetch(e.target.value)}
          placeholder="Enter ID"
        />
        <button className="btn-blue" onClick={getPlayerById}>Fetch</button>

        {fetchedPlayer && (
          <div>
            <h4>Player Found:</h4>
            <pre>{JSON.stringify(fetchedPlayer, null, 2)}</pre>
          </div>
        )}
      </div>

      <div>
        <h3>All Players</h3>
        {players.length === 0 ? (
          <p>No players found.</p>
        ) : (
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  {Object.keys(player).map((key) => (
                    <th key={key}>{key}</th>
                  ))}
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {players.map((p) => (
                  <tr key={p.id}>
                    {Object.keys(player).map((key) => (
                      <td key={key}>{p[key]}</td>
                    ))}
                    <td>
                      <div className="action-buttons">
                        <button className="btn-green" onClick={() => handleEdit(p)}>Edit</button>
                        <button className="btn-red" onClick={() => deletePlayer(p.id)}>Delete</button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>

    </div>
  );
};

export default SportsManager;
