import React, { useState } from 'react';
import JDInput from './components/JDInput';
import ResumeEditor from './components/ResumeEditor';
import SuggestionsPanel from './components/SuggestionsPanel';
import ExportButton from './components/ExportButton';
import './App.css';

function App() {
  const [jdText, setJDText] = useState(""); // Job Description
  const [resumeLines, setResumeLines] = useState([]); // Resume content line-by-line
  const [suggestions, setSuggestions] = useState([]); // Suggestions from backend

  const getSuggestions = async () => {
    try {
      const response = await fetch("http://localhost:8080/getSuggestions", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ jd: jdText, resume: resumeLines })
      });

      if (!response.ok) {
        throw new Error("Failed to fetch suggestions");
      }

      const data = await response.json();
      setSuggestions(data);
    } catch (error) {
      console.error("Error fetching suggestions:", error);
      alert("Something went wrong while fetching suggestions.");
    }
  };

  const cleanText = (text) => {
    return text.replace(/^"(.*)"$/, "$1").replace(/\\"/g, '"').replace(/\\\//g, '/');
  };

  const applySuggestion = (index) => {
    const updated = [...resumeLines];
    updated[index] = cleanText(suggestions[index].suggested);
    setResumeLines(updated);
  };

  return (
    <div className="p-6 max-w-4xl mx-auto bg-gray-100 min-h-screen">
      <header className="text-center mb-8">
        <h1 className="text-3xl font-bold text-indigo-600">Smart Resume Tailor</h1>
        <p className="text-gray-600 mt-2">Tailor your resume to match job descriptions effortlessly</p>
      </header>

      <div className="bg-white shadow-md rounded-lg p-6">
        <JDInput jdText={jdText} setJDText={setJDText} />
      </div>

      <div className="bg-white shadow-md rounded-lg p-6 mt-6">
        <ResumeEditor resumeLines={resumeLines} setResumeLines={setResumeLines} />
      </div>

      <div className="text-center mt-6">
        <button
          onClick={getSuggestions}
          className="bg-indigo-600 hover:bg-indigo-700 text-white px-6 py-3 rounded-lg shadow-md transition duration-300"
        >
          Get Suggestions
        </button>
      </div>

      <div className="bg-white shadow-md rounded-lg p-6 mt-6">
        <SuggestionsPanel suggestions={suggestions} applySuggestion={applySuggestion} />
      </div>

      <div className="text-center mt-6">
        <ExportButton resumeLines={resumeLines} />
      </div>
    </div>
  );
}

export default App;
