import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import { Home, Search, Folder, Settings, LogOut, Play, ChevronLeft, ChevronRight } from 'lucide-react';

function Sidebar({ isOpen, onToggle, onLogout }) {
  const location = useLocation();

  const navItems = [
    { path: '/', icon: Home, label: 'Home' },
    { path: '/search', icon: Search, label: 'Search' },
    { path: '/collections', icon: Folder, label: 'Collections' },
  ];

  return (
    <>
      <button
        onClick={onToggle}
        className="fixed top-4 left-4 z-50 p-2 bg-zinc-800 hover:bg-zinc-700 rounded-lg transition-colors"
        data-testid="sidebar-toggle"
      >
        {isOpen ? <ChevronLeft className="w-5 h-5" /> : <ChevronRight className="w-5 h-5" />}
      </button>

      <aside className={`sidebar ${!isOpen ? 'closed' : ''}`}>
        <div className="sidebar-header">
          <div className="flex items-center gap-2">
            <Play className="w-8 h-8 text-red-600" fill="currentColor" />
            <div className="sidebar-logo">MyVidster</div>
          </div>
        </div>

        <nav className="sidebar-nav">
          {navItems.map((item) => {
            const Icon = item.icon;
            const isActive = location.pathname === item.path;
            return (
              <Link
                key={item.path}
                to={item.path}
                className={isActive ? 'active' : ''}
                data-testid={`nav-${item.label.toLowerCase()}`}
              >
                <Icon className="w-5 h-5" />
                {item.label}
              </Link>
            );
          })}
        </nav>

        <div className="sidebar-footer space-y-2">
          <Link to="/settings" className="flex items-center gap-3 text-gray-400 hover:text-white transition-colors p-2 rounded">
            <Settings className="w-5 h-5" />
            <span>Settings</span>
          </Link>
          <button
            onClick={onLogout}
            className="flex items-center gap-3 text-gray-400 hover:text-red-500 transition-colors p-2 rounded w-full"
            data-testid="logout-button"
          >
            <LogOut className="w-5 h-5" />
            <span>Logout</span>
          </button>
        </div>
      </aside>
    </>
  );
}

export default Sidebar;
