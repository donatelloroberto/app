import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Play, Clock, TrendingUp } from 'lucide-react';

function HomePage() {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [videos, setVideos] = useState([]);

  // Sample data for demonstration
  // In production, this would fetch from the API
  const sampleVideos = [
    {
      id: '1',
      title: 'Sample Video 1',
      thumbnail: 'https://via.placeholder.com/320x180/333/fff?text=Video+1',
      duration: '10:30',
      views: '1.2M'
    },
    {
      id: '2',
      title: 'Sample Video 2',
      thumbnail: 'https://via.placeholder.com/320x180/333/fff?text=Video+2',
      duration: '15:45',
      views: '890K'
    },
    {
      id: '3',
      title: 'Sample Video 3',
      thumbnail: 'https://via.placeholder.com/320x180/333/fff?text=Video+3',
      duration: '8:20',
      views: '2.1M'
    },
  ];

  useEffect(() => {
    // Load videos
    setVideos(sampleVideos);
  }, []);

  const handleVideoClick = (videoId) => {
    navigate(`/video/${videoId}`);
  };

  return (
    <div className="p-6">
      <div className="mb-8">
        <h1 className="text-3xl font-bold mb-2">Welcome to MyVidster Desktop</h1>
        <p className="text-gray-400">Discover and organize your favorite videos</p>
      </div>

      <div className="mb-6">
        <div className="flex gap-4 mb-6">
          <button className="btn btn-secondary">
            <TrendingUp className="w-5 h-5" />
            Trending
          </button>
          <button className="btn btn-secondary">
            <Clock className="w-5 h-5" />
            Recent
          </button>
        </div>
      </div>

      {loading ? (
        <div className="flex items-center justify-center h-64">
          <div className="loading-spinner" />
        </div>
      ) : (
        <div className="video-grid">
          {videos.map((video) => (
            <div
              key={video.id}
              className="video-card"
              onClick={() => handleVideoClick(video.id)}
              data-testid={`video-card-${video.id}`}
            >
              <div className="relative">
                <img src={video.thumbnail} alt={video.title} />
                <div className="absolute bottom-2 right-2 bg-black/80 px-2 py-1 rounded text-xs">
                  {video.duration}
                </div>
                <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 opacity-0 hover:opacity-100 transition-opacity">
                  <Play className="w-12 h-12 text-white" fill="white" />
                </div>
              </div>
              <div className="video-card-content">
                <h3 className="video-card-title">{video.title}</h3>
                <div className="video-card-meta">
                  {video.views} views
                </div>
              </div>
            </div>
          ))}
        </div>
      )}

      <div className="mt-12 text-center text-gray-500">
        <p>Connect your MyVidster account to see your personalized content</p>
        <p className="text-sm mt-2">Use the search feature to find videos or browse collections</p>
      </div>
    </div>
  );
}

export default HomePage;
