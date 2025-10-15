import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import './App.css';
import myvidsterApi from './services/myvidsterApi';
import LoginPage from './components/LoginPage';
import HomePage from './components/HomePage';
import VideoPlayer from './components/VideoPlayer';
import SearchPage from './components/SearchPage';
import CollectionsPage from './components/CollectionsPage';
import Sidebar from './components/Sidebar';

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  const [sidebarOpen, setSidebarOpen] = useState(true);

  useEffect(() => {
    // Check if user is already authenticated
    const checkAuth = async () => {
      await myvidsterApi.loadCredentials();
      setIsAuthenticated(myvidsterApi.isAuthenticated());
      setIsLoading(false);
    };
    checkAuth();
  }, []);

  const handleLogin = async (email, password) => {
    await myvidsterApi.saveCredentials(email, password);
    setIsAuthenticated(true);
  };

  const handleLogout = () => {
    myvidsterApi.clearCredentials();
    setIsAuthenticated(false);
  };

  if (isLoading) {
    return (
      <div className="flex items-center justify-center h-screen bg-black">
        <div className="text-white text-2xl">Loading...</div>
      </div>
    );
  }

  if (!isAuthenticated) {
    return <LoginPage onLogin={handleLogin} />;
  }

  return (
    <Router>
      <div className="flex h-screen bg-black text-white">
        <Sidebar 
          isOpen={sidebarOpen} 
          onToggle={() => setSidebarOpen(!sidebarOpen)}
          onLogout={handleLogout}
        />
        <main className="flex-1 overflow-y-auto">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/search" element={<SearchPage />} />
            <Route path="/collections" element={<CollectionsPage />} />
            <Route path="/video/:videoId" element={<VideoPlayer />} />
            <Route path="*" element={<Navigate to="/" replace />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
