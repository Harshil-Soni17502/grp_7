import React, { useState } from 'react';
import './DropdownPage.css'; // Make sure to create this CSS file in the same directory

function DropdownPage() {
  const [selectedOption, setSelectedOption] = useState('');
  const isOptionSelected = selectedOption !== '';

  const handleOptionChange = (event) => {
    setSelectedOption(event.target.value);
  };

  return (
    <div className="page-container">
      <div className={`content ${isOptionSelected ? 'visible' : 'hidden'}`}>
        <h1>Page Content</h1>
        <p>This is your page content.</p>
      </div>
      <div className={`overlay ${isOptionSelected ? 'hidden' : ''}`} />
      <select value={selectedOption} onChange={handleOptionChange}>
        <option value="">Select an option</option>
        <option value="option1">Option 1</option>
        <option value="option2">Option 2</option>
        <option value="option3">Option 3</option>
      </select>
    </div>
  );
}

export default DropdownPage;
