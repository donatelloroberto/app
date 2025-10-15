import React, { useState } from 'react';
import { Search, Filter } from 'lucide-react';
import { useNavigate } from 'react-router-dom';

function SearchPage() {
  const navigate = useNavigate();
  const [searchQuery, setSearchQuery] = useState('');
  const [loading, setLoading] = useState(false);
  const [results, setResults] = useState([]);

  const handleSearch = async (e) => {
    e.preventDefault();
    if (!searchQuery.trim()) return;

    setLoading(true);
    try {
      // API search would go here
      // For now, using placeholder
      await new Promise(resolve => setTimeout(resolve, 1000));
      setResults([]);
    } catch (error) {
      console.error('Search error:', error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="p-6">
      <div className="max-w-4xl mx-auto">
        <div className="mb-8">
          <h1 className="text-3xl font-bold mb-4">Search Videos</h1>
          
          <form onSubmit={handleSearch} className="search-bar">
            <input
              type="text"
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              placeholder="Search for videos..."
              className="input"
              data-testid="search-input"
            />
            <button type="submit" disabled={loading} data-testid="search-button">
              <Search className="w-5 h-5" />
            </button>
          </form>
        </div>

        <div className="flex items-center justify-between mb-6">
          <h2 className="text-xl font-semibold">
            {results.length > 0 ? `${results.length} Results` : 'Search Results'}
          </h2>
          <button className="btn btn-secondary">
            <Filter className="w-4 h-4" />
            Filter
          </button>
        </div>

        {loading ? (
          <div className="flex items-center justify-center h-64">
            <div className="loading-spinner" />
          </div>
        ) : results.length === 0 ? (
          <div className="text-center py-20">
            <Search className="w-16 h-16 text-gray-600 mx-auto mb-4" />
            <h3 className="text-xl font-semibold mb-2">No results yet</h3>
            <p className="text-gray-400">
              {searchQuery ? 'Try a different search term' : 'Enter a search term to find videos'}
            </p>
          </div>
        ) : (
          <div className="video-grid">
            {results.map((video) => (
              <div
                key={video.id}
                className="video-card"
                onClick={() => navigate(`/video/${video.id}`)}
              >
                <img src={video.thumbnail} alt={video.title} />
                <div className="video-card-content">
                  <h3 className="video-card-title">{video.title}</h3>
                  <div className="video-card-meta">{video.views} views</div>
                </div>
              </div>
            ))}
          </div>
        )}

        <div className="mt-12 bg-zinc-900 rounded-lg p-6">
          <h3 className="text-lg font-semibold mb-3">Search Tips</h3>
          <ul className="space-y-2 text-gray-400 text-sm">
            <li>• Use specific keywords for better results</li>
            <li>• Try different combinations if you don't find what you're looking for</li>
            <li>• Check your spelling and try again</li>
          </ul>
        </div>
      </div>
    </div>
  );
}

export default SearchPage;
