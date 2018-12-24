import cytoscape from 'cytoscape';
import React, { PureComponent, createRef } from 'react';
import styled from 'styled-components';

class MapComponent extends PureComponent {
  mapGraphRef = createRef();

  mapGraph;

  componentDidMount () {
    this.renderMapGraph();
  }

  renderMapGraph = () => {
    this.mapGraph = cytoscape({
      container: this.mapGraphRef.current,
    });
  };

  render () {
    return <div className={this.props.className} ref={this.mapGraphRef} />;
  }
}

export const Map = styled(MapComponent)`
  background: ${(props) => `url(${require(`../images/${props.name}floor`)}) no-repeat center`};
  background-size: contain;
  min-height: 450px;
`;
